/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.core.rpc.processor.client;

import io.netty.channel.ChannelHandlerContext;
import io.seata.core.protocol.LeaderNotifyRequest;
import io.seata.core.protocol.RpcMessage;
import io.seata.core.rpc.netty.RmNettyRemotingClient;
import io.seata.core.rpc.processor.RemotingProcessor;

/**
 * process TC leader notify.
 * <p>
 * process message type:
 * {@link LeaderNotifyRequest}
 *
 * @author funkye
 * @since 2.0.0
 */
public class RmModifyLeaderProcessor implements RemotingProcessor {

    @Override
    public void process(ChannelHandlerContext ctx, RpcMessage rpcMessage) throws Exception {
        String[] address = ((LeaderNotifyRequest)rpcMessage.getBody()).getAddress().split(":");
        String ip = address[0];
        int port = Integer.parseInt(address[1]);
        RmNettyRemotingClient.getInstance().modifyLeader(ip, port);
    }

}
